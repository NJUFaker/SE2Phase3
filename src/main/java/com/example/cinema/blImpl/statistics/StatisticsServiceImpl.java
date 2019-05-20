package com.example.cinema.blImpl.statistics;

import com.example.cinema.bl.statistics.StatisticsService;
import com.example.cinema.data.statistics.StatisticsMapper;
import com.example.cinema.po.AudiencePrice;
import com.example.cinema.po.Hall;
import com.example.cinema.po.MovieScheduleTime;
import com.example.cinema.po.MovieTotalBoxOffice;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author fjj
 * @date 2019/4/16 1:34 PM
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsMapper statisticsMapper;
    @Override
    public ResponseVO getScheduleRateByDate(Date date) {
        try{

            Date requireDate = date;
            if(requireDate == null){
                requireDate = new Date();
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            requireDate = simpleDateFormat.parse(simpleDateFormat.format(requireDate));

            Date nextDate = getNumDayAfterDate(requireDate, 1);
            return ResponseVO.buildSuccess(movieScheduleTimeList2MovieScheduleTimeVOList(statisticsMapper.selectMovieScheduleTimes(requireDate, nextDate)));

        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getTotalBoxOffice() {
        try {
            return ResponseVO.buildSuccess(movieTotalBoxOfficeList2MovieTotalBoxOfficeVOList(statisticsMapper.selectMovieTotalBoxOffice()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getAudiencePriceSevenDays() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date today = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            Date startDate = getNumDayAfterDate(today, -6);
            List<AudiencePriceVO> audiencePriceVOList = new ArrayList<>();
            for(int i = 0; i < 7; i++){
                AudiencePriceVO audiencePriceVO = new AudiencePriceVO();
                Date date = getNumDayAfterDate(startDate, i);
                audiencePriceVO.setDate(date);
                List<AudiencePrice> audiencePriceList = statisticsMapper.selectAudiencePrice(date, getNumDayAfterDate(date, 1));
                double totalPrice = audiencePriceList.stream().mapToDouble(item -> item.getTotalPrice()).sum();
                audiencePriceVO.setPrice(Double.parseDouble(String.format("%.2f", audiencePriceList.size() == 0 ? 0 : totalPrice / audiencePriceList.size())));
                audiencePriceVOList.add(audiencePriceVO);
            }
            return ResponseVO.buildSuccess(audiencePriceVOList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getMoviePlacingRateByDate(Date date) {
        try{
            ArrayList<String> x=null;
            x.add("?");
            List<MovieTotalBoxOffice> movieTotalBoxOffices=statisticsMapper.selectAudienceNum(date,getNumDayAfterDate(date,1));
            List<PlacingRateVO> placingRateVOList=new ArrayList<>();
            List<MovieScheduleTime> movieScheduleTimeList=statisticsMapper.selectMovieScheduleTimes(date,getNumDayAfterDate(date,1));
            int totalSeats=0,hallNums=0;//座位数，影厅数


            List<Hall> halls=statisticsMapper.selectTotalHalls();

            for(int j=0;j<halls.size();j++){
                totalSeats=totalSeats+halls.get(j).getColumn()*halls.get(j).getRow();
                hallNums++;
            }

            double placingRate=0;
            double AudienceNum;

            for(int i=0;i<movieTotalBoxOffices.size();i++){
                AudienceNum=0;
                for(int j=0;j<movieScheduleTimeList.size();j++){
                    if(movieTotalBoxOffices.get(i).getMovieId()==movieScheduleTimeList.get(j).getMovieId()){
                        AudienceNum=movieTotalBoxOffices.get(i).getBoxOffice()+0.0;
                    }
                }
                if(AudienceNum==0){
                    placingRateVOList.add(new PlacingRateVO(movieTotalBoxOffices.get(i).getMovieId(),0));
                    continue;
                }
                placingRate=AudienceNum/totalSeats/movieScheduleTimeList.get(i).getTime();
                placingRateVOList.add(new PlacingRateVO(movieTotalBoxOffices.get(i).getMovieId(),placingRate));
            }

            return ResponseVO.buildSuccess(placingRateVOList);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
        //要求见接口说明
    }

    @Override
    public ResponseVO getPopularMovies(int days, int movieNum) {
        //要求见接口说明
        try{
            ArrayList<String> x=null;
            x.add("?");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date today = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            List<MovieTotalBoxOffice> movieTotalBoxOffices=statisticsMapper.selectMovieBoxOfficeOnCertainDate(today,getNumDayAfterDate(today,days));
            List<PopularMoviePO> popularMoviePOS=new ArrayList<>();

            for(int i=0;i<movieNum;i++){
                PopularMoviePO popularMoviePO=new PopularMoviePO();
                popularMoviePO.setMovieId(movieTotalBoxOffices.get(i).getMovieId());
                popularMoviePO.setName(movieTotalBoxOffices.get(i).getName());
                popularMoviePO.setPopularRank(i+1);
                popularMoviePOS.add(popularMoviePO);
            }
            return ResponseVO.buildSuccess(popularMoviePOS);
        }catch (Exception e){
            return ResponseVO.buildFailure("失败");
        }
    }


    /**
     * 获得num天后的日期
     * @param oldDate
     * @param num
     * @return
     */
    Date getNumDayAfterDate(Date oldDate, int num){
        Calendar calendarTime = Calendar.getInstance();
        calendarTime.setTime(oldDate);
        calendarTime.add(Calendar.DAY_OF_YEAR, num);
        return calendarTime.getTime();
    }



    private List<MovieScheduleTimeVO> movieScheduleTimeList2MovieScheduleTimeVOList(List<MovieScheduleTime> movieScheduleTimeList){
        List<MovieScheduleTimeVO> movieScheduleTimeVOList = new ArrayList<>();
        for(MovieScheduleTime movieScheduleTime : movieScheduleTimeList){
            movieScheduleTimeVOList.add(new MovieScheduleTimeVO(movieScheduleTime));
        }
        return movieScheduleTimeVOList;
    }


    private List<MovieTotalBoxOfficeVO> movieTotalBoxOfficeList2MovieTotalBoxOfficeVOList(List<MovieTotalBoxOffice> movieTotalBoxOfficeList){
        List<MovieTotalBoxOfficeVO> movieTotalBoxOfficeVOList = new ArrayList<>();
        for(MovieTotalBoxOffice movieTotalBoxOffice : movieTotalBoxOfficeList){
            movieTotalBoxOfficeVOList.add(new MovieTotalBoxOfficeVO(movieTotalBoxOffice));
        }
        return movieTotalBoxOfficeVOList;
    }
}
