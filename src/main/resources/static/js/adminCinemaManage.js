$(document).ready(function() {

    //选择是否显示root权限
    var role=sessionStorage.getItem('role')
    console.log(role)
    console.log(role==='root')
    if (role==='root'){
        $('#root-staff-manage').css("display","")
    }

    var canSeeDate = 0;

    getCanSeeDayNum();
    getCinemaHalls();

    function getCinemaHalls() {
        var halls = [];
        getRequest(
            '/hall/all',
            function (res) {
                console.log(res.content)
                halls = res.content;
                renderHall(halls);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    function renderHall(halls){
        $('#hall-card').empty();
        var hallDomStr = "";
        halls.forEach(function (hall) {
            var seat = "";
            for(var i =0;i<hall.row;i++){
                var temp = ""
                for(var j =0;j<hall.column;j++){
                    temp+="<div class='cinema-hall-seat'></div>";
                }
                seat+= "<div>"+temp+"</div>";
            }
            var link='<button type="button" class="btn btn-primary edit-hall"data-backdrop="static" data-toggle="modal" data-target="#edithall"  data-card='+JSON.stringify(hall) +'> 编辑</button>'

            var hallDom =
                "<div class='cinema-hall'>" +
                "<div>" +
                "<span class='cinema-hall-name'>"+ hall.name +"</span>" +
                "<span class='cinema-hall-size'>"+ hall.column +'*'+ hall.row +"</span>" +
                link+
                "</div>" +
                "<div class='cinema-seat'>" + seat +
                "</div>" +
                "</div>";
            hallDomStr+=hallDom;
        });
        $('#hall-card').append(hallDomStr);
    }

    function getCanSeeDayNum() {
        getRequest(
            '/schedule/view',
            function (res) {
                canSeeDate = res.content;
                $("#can-see-num").text(canSeeDate);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    $('#canview-modify-btn').click(function () {
       $("#canview-modify-btn").hide();
       $("#canview-set-input").val(canSeeDate);
       $("#canview-set-input").show();
       $("#canview-confirm-btn").show();
    });

    $('#canview-confirm-btn').click(function () {
        var dayNum = $("#canview-set-input").val();
        // 验证一下是否为数字
        postRequest(
            '/schedule/view/set',
            {day:dayNum},
            function (res) {
                if(res.success){
                    getCanSeeDayNum();
                    canSeeDate = dayNum;
                    $("#canview-modify-btn").show();
                    $("#canview-set-input").hide();
                    $("#canview-confirm-btn").hide();
                } else{
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    })


    $("#card-form-btn").click(function () {
        var card=getCardForm();
        if (validCardForm(card)){
            card.name="满减"
            card.decription="满减策略"
            postRequest(
                '/vipActivity/publish',
                card
                ,
                function (res) {
                    // console.log(res)
                    if (res.message){
                        alert(res.message)
                    }
                    else {
                        alert("添加成功")
                        getAllCard()
                        $('#addcard').modal('hide')
                    }
                },
                function (err) {
                    alert(err.message)
                }
            )
        }

        else {
            return;
        }

    })

    //编辑按钮
    //显示编辑框
    $(document).on('click','.edit-staff',function (e) {
        var card=JSON.parse(e.target.dataset.card)
        // console.log(user)
        $('#VIPcard-money-edit-input').val(card.costInNeed);
        $('#discount-edit-input').val(card.bonusBalance);

        $('#card-form-edit-btn').attr("data-id",card.id)
    })

    // 点击确认按钮
    $("#card-form-edit-btn").click(function () {
        var card=getCardForm()
        if (getCardForm(card)){
            card.name="满减"
            card.decription="满减策略"
            card.id=$('#card-form-edit-btn').attr("data-id")
            postRequest(
                '/vipActivity/update',
                card
                ,
                function (res) {
                    // console.log(res)
                    if (res.message){
                        alert(res.message)
                    }
                    else {
                        alert("修改成功")
                        getAllCard()
                        $('#addcardedit').modal('hide')
                    }
                },
                function (err) {
                    alert(JSON.stringify(err))
                }
            )
        }

        else {
            return;
        }

    });


    function getHallorm() {
        return{
            name:$("#hall-name-input").val()||$('#edit-hall-name-input').val(),
            row:$("#row-input").val()||$('#edit-row-input').val(),
            column:$("#column-input").val()||$('#edit-column-input').val()
        }
    }

    function validHallForm(hall) {
        var isValidUser=true
        if (!hall.name){
            isValidUser=false
            alert("请输入名称")
        }
        if (!hall.row){
            isValidUser=false
            alert("请输入行数")

        }

        if (!hall.column){
            isValidUser=false
            alert("请输入列数")

        }
        return isValidUser
    }
    //隐藏modal执行清空
    $('#addcard').on('hide.bs.modal', function () {
        $("#VIPcard-money-input").val('')
        $("#discount-input").val('')
    })

    $('#addcardedit').on('hide.bs.modal', function () {
        $("#VIPcard-money-edit-input").val('')
        $("#discount-edit-input").val('')
    })
});