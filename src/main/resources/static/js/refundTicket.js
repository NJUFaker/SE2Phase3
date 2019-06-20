
$(document).ready(function () {
<<<<<<< HEAD

    // console.log(getTime(86430))
    // console.log(parseInt(12/5))
    // console.log(parseInt(14/5))
    // console.log(parseInt(13%5))
    getRefundStrategy();
    function getRefundStrategy() {
        getRequest(
            '/refund/get',
            function (res) {
                console.log(res)
                if (res.success) {
                    renderRefundList(res.content)
                }
                else {
                    alert(res.message)
                }
            },
            function (err) {
                console.log(err)

            }
        )
    }


    function renderRefundList(list) {
        // console.log("render")
        $('.refund-table-body').empty()
        for (var i=0;i<list.length;i++){
            var stra=list[i]
            var temp=getTime(stra.availableTime)
            stra.day=temp.day
            stra.hour=temp.hour
            stra.min=temp.min
            stra.sec=temp.sec
            console.log(stra)
            var link='<button type="button" class="btn btn-primary edit-refund"data-backdrop="static" data-toggle="modal" data-target="#editRefund"  data-user='+JSON.stringify(stra) +'> 编辑</button>'
            var line=$('<tr>'+'<td>'+stra.day+"天"+stra.hour+"小时"+stra.min+"分钟"+stra.sec+"秒"+'</td>'+'<td>'+stra.refundPercentage+'</td>'+'<td>'+link+'</td>'+'</tr>')
            $("#refund-table-body-in").append(line)
=======
    getrefundStrategy();
    function getrefundStrategy() {
        return{
            // strategy:$("#refundStrategy-description-input").val(),
            refundtime:$("#refund-time-input").val(),
            percentage:Math.round(parseFloat($("#refundPercentage-input").val()))

        }
        console.log("wo")
    }
    console.log("wo")
    getAllStaff();


    function getAllStaff() {
        getRequest(
            '/refund/add',
            function (res) {
                if (res.success){
                    // $('#staff-line').remove()
                    console.log(res)
                    renderStaffList(res.content)
                }

            })

    }

    function renderStaffList(list) {
        // console.log("render")
        $('.staff-table-body').empty()
        for (var i=0;i<list.length;i++){
            var user=list[i]
            // var userStr=JSON.stringify(user)
            //root不能被删除
            var link=""
            if (user.username==="root"){
                link='<button type="button" class="btn btn-primary edit-staff"data-backdrop="static" data-toggle="modal" data-target="#editStaff"  data-user='+JSON.stringify(user) +'> 编辑</button>'

            }
            else {
                link='<button type="button" class="btn btn-primary edit-staff"data-backdrop="static" data-toggle="modal" data-target="#editStaff"  data-user='+JSON.stringify(user) +'> 编辑</button>'+' <button type="button" class="btn btn-primary deleteStaff"  id="delete-staff" data-name='+user.username+'> 删除</button>'

            }
            var line=$('<tr>'+'<td>'+user.username+'</td>'+'<td>'+user.password+'</td>'+'<td>'+link+'</td>'+'</tr>')
            $("#staff-table-body-in").append(line)
>>>>>>> bb0ae0b4372af33ff54dcf2413bd89157b38fc33
        }
    }

    $("#activity-form-btn").click(function () {
<<<<<<< HEAD
        var temp=getRefundForm()
        if (validRefundForm(temp)){
            var refund={
                availableTime:setTime(temp.day,temp.hour,temp.min,temp.sec),
                refundPercentage:temp.per
            }
            postRequest(
                '/refund/add',
                refund
=======
        var ticket=getrefundStrategy();
        console.log(ticket)
        if (validUserForm(ticket)){
            postRequest(
                '/refund/add',
                ticket
>>>>>>> bb0ae0b4372af33ff54dcf2413bd89157b38fc33
                ,
                function (res) {
                    console.log(res)
                    if (res.message){
                        alert(res.message)
                    }
                    else {
                        alert("添加成功")
<<<<<<< HEAD
                        getRefundStrategy()
                        $(':text').val('')
                        $('#activityModal').modal('hide')
=======
                        getAllStaff()
                        $(':text').val('')
                        $('#addStaff').modal('hide')
>>>>>>> bb0ae0b4372af33ff54dcf2413bd89157b38fc33
                    }
                },
                function (err) {
                    alert(err.message)
                })
        }
        else {
            return;
        }
    })

<<<<<<< HEAD
    //显示编辑框
    $(document).on('click','.edit-refund',function (e) {
        var refund=JSON.parse(e.target.dataset.user)

        console.log(refund)
        $('#edit-refund-day-input').val(refund.day);
        $('#edit-refund-hour-input').val(refund.hour);
        $('#edit-refund-min-input').val(refund.min);
        $('#edit-refund-sec-input').val(refund.sec);
        $('#edit-refund-percentage-input').val(refund.refundPercentage);

        $('#edit-refund-btn').attr("data-id",refund.id)
    })
=======
>>>>>>> bb0ae0b4372af33ff54dcf2413bd89157b38fc33

    
})

$(".edit-refund-btn").click(function () {
    var temp=getRefundForm()
    if (validRefundForm(temp)){
        var refund={
            availableTime:setTime(temp.day,temp.hour,temp.min,temp.sec),
            refundPercentage:temp.per,
            id:$('#edit-refund-btn').attr("data-id")
        }
        postRequest(
            refund/update,
            refund
            ,
            function (res) {
                // console.log(res)
                if (res.message){
                    alert(res.message)
                }
                else {
                    alert("修改成功")
                    getRefundStrategy()
                    $(':text').val('')
                    $('#activityModal').modal('hide')
                }
            },
            function (err) {
                alert(err.message)
            })
    }
    else {
        return;
    }

})

function getTime(second) {
    var day=parseInt(second/86400)
    var temp=second%86400
    var hour=parseInt(temp/3600)
    temp=temp%3600
    // console.log(temp)
    var min=parseInt(temp/60)
    var sec=temp%60
    return {
        day:day,
        hour:hour,
        min:min,
        sec:sec
    }
}

function setTime(day,h,m,s) {
    // console.log(parseInt(day*86400)+parseInt(h*3600)+parseInt(m*60)+parseInt(s))
    return parseInt(day*86400)+parseInt(h*3600)+parseInt(m*60)+parseInt(s)
}

function getRefundForm() {
    return{
        day:$("#new-refund-day-input").val()||$('#edit-refund-day-input').val(),
        hour:$("#new-refund-hour-input").val()||$('#edit-refund-hour-input').val(),
        min:$("#new-refund-min-input").val()||$('#edit-refund-min-input').val(),
        sec:$("#new-refund-sec-input").val()||$('#edit-refund-sec-input').val(),
        per:$("#new-refund-percentage-input").val()||$('#edit-refund-percentage-input').val(),

    }
}

function validRefundForm(ref) {
    if (!ref.day) console.log("asdffff")
    var isValidUser=true
    if (!(ref.hour&&ref.hour&&ref.min&&ref.sec&&ref.hour<25&&ref.hour>=0&&ref.min<61&&ref.min>=0&&ref.sec<61&&ref.sec>=0)){
        isValidUser=false
        alert("请输入正确的时间")
    }
    if (!ref.per||ref.per>1&&ref.per<0){
        isValidUser=false
        alert("请输入正确的退款比例")
    }
        return isValidUser
}
//隐藏modal执行清空
$('#activityModal').on('hide.bs.modal', function () {
    $(':text').val('')
})