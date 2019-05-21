 $(document).ready(function () {
     var count=0
    getMovieList();

    function getMovieList() {
        getRequest(
            '/ticket/get/' + sessionStorage.getItem('id'),
            function (res) {
                // console.log(res)
                renderTicketList(res.content);
            },
            function (error) {
                alert(error);
            });
    }

    // TODO:填空
    function renderTicketList(list) {
        console.log(list)
        for (var i = 0; i < list.length; i++) {
            let tempVO=list[i]
            if (tempVO.state=="未完成") {
                continue;
            }
            let state=""
            let seat=""
            seat="<td>"+(tempVO.rowIndex+1)+"排"+(tempVO.columnIndex+1)+"座"+"</td>"
            state="<td>"+tempVO.state+"</td>"
            console.log("enter")
            console.log(tempVO)
            getRequest(
                "/schedule/"+tempVO.scheduleId,
                function (res) {
                    console.log("本次排片")
                    console.log(res)
                    let tempSche=res.content
                    let name="<td>"+tempSche.movieName+"</td>"
                    let hallname="<td>"+tempSche.hallName+"</td>"
                    let sTime="<td>"+tempSche.startTime.substring(11, 16)+"</td>"
                    let eTime="<td>"+tempSche.endTime.substring(11, 16)+"</td>"
                    let tempStr="<tr>"+name+hallname+seat+sTime+eTime+state+"</tr>"
                    console.log("得到每条数据的内容")
                    console.log(tempStr)
                    $('#ticket-table-body-in').append(tempStr)
                }
            );
            console.log("lalalalla")
            console.log(sessionStorage.getItem(count))


            //temp+=t
            // for (var j = 0; j < seats[i].length; j++) {
            //     var id = "seat" + i + j
            //
            //     if (seats[i][j] == 0) {
            //         // 未选
            //         temp += "<button class='cinema-hall-seat-choose' id='" + id + "' onclick='seatClick(\"" + id + "\"," + i + "," + j + ")'></button>";
            //     } else {
            //         // 已选中
            //         temp += "<button class='cinema-hall-seat-lock'></button>";
            //     }
            // }
        }
        // var hallDom =
        //     "<div class='cinema-hall'>" +
        //     "<div>" +
        //     "<span class='cinema-hall-name'>" + schedule.hallName + "</span>" +
        //     "<span class='cinema-hall-size'>" + seats.length + '*' + seats[0].length + "</span>" +
        //     "</div>" +
        //     "<div class='cinema-seat'>" + seat +
        //     "</div>" +
        //     "</div>";
        // hallDomStr += hallDom;
        //
        // $('#hall-card').html(hallDomStr);
    }

});