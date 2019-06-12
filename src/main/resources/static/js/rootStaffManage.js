$(document).ready(function () {

    getAllStaff();

    //得到全部管理员
    function getAllStaff() {
        getRequest(
            '/accountOfManager/get/all',
            function (res) {
                if (res.success){
                    // $('#staff-line').remove()
                    // console.log(res)
                    renderStaffList(res.content)
                }

            }
        )
        
    }
    //
    function renderStaffList(list) {
        // console.log("render")
        $('.staff-table-body').empty()
        for (var i=0;i<list.length;i++){
            var user=list[i]
            // var userStr=JSON.stringify(user)
            var link='<button type="button" class="btn btn-primary edit-staff"data-backdrop="static" data-toggle="modal" data-target="#editStaff"  data-user='+JSON.stringify(user) +'> 编辑</button>'+' <button type="button" class="btn btn-primary deleteStaff"  id="delete-staff" data-name='+user.username+'> 删除</button>'
            var line=$('<tr>'+'<td>'+user.username+'</td>'+'<td>'+user.password+'</td>'+'<td>'+link+'</td>'+'</tr>')
            $("#staff-table-body-in").append(line)
        }
    }



    $("#staff-add-btn").click(function () {
        var user=getUserForm();
        if (validUserForm(user)){
            postRequest(
                '/accountOfManager/add',
                user
                ,
                function (res) {
                    // console.log(res)
                    if (res.message){
                        alert(res.message)
                    }
                    else {
                        alert("添加成功")
                        getAllStaff()
                        $(':text').val('')
                        $('#addStaff').modal('hide')
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
        var user=JSON.parse(e.target.dataset.user)
        // console.log(user)
        $('#staff-edit-username-input').val(user.username);
        $('#staff-edit-password-input').val(user.password);
        $('#staff-edit-btn').attr("data-oldname",user.username)
    })
    // 点击确认按钮
    $(".editStaff").click(function () {
        var user=getUserForm();
        console.log("GET FORM")
        console.log(user)
        if (validUserForm(user)){
            user.oldname=$("#staff-edit-btn").attr("data-oldname")
            console.log("edit")
            console.log(user)
            postRequest(
                '/accountOfManager/update',
                user
                ,
                function (res) {
                    // console.log(res)
                    if (res.message){
                        alert(res.message)
                    }
                    else {
                        alert("修改成功")
                        getAllStaff()
                        $('#editStaff').modal('hide')
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

    })


    //删除按钮

    $(document).on('click','.deleteStaff',function (e) {
        var r=confirm("确认删除本管理员？")
        if (r){
            var name=e.target.dataset.name
           deleteRequest(
               '/accountOfManager/delete?name='+name,
               null,
               function (res) {
                   if (res.message){
                       alert(res.message)
                   }
                   else {
                       alert("已删除")
                       getAllStaff()
                   }
               },
               function (err) {
                   alert(JSON.stringify(err))
               }
           )
        }

        $('#staff-edit-btn').attr("data-oldname",user.username)
    })
    $('.deleteStaff').click(function () {


    })


    function getUserForm() {
        return{
            username:$("#staff-username-input").val()||$('#staff-edit-username-input').val(),
            password:$("#staff-password-input").val()||$('#staff-edit-password-input').val()
        }
    }

    function validUserForm(user) {
        var isValidUser=true
        if (!user.username){
            isValidUser=false
            $('#staff-username-input').parent('.form-group').addClass('has-error');
        }
        if (!user.password){
            isValidUser=false
            $('#staff-password-input').parent('.form-group').addClass('has-error');
        }
        // console.log(isValidUser)
        return isValidUser
    }
    //隐藏modal执行清空
    $('#addStaff').on('hide.bs.modal', function () {
        $(':text').val('')
    })
    // TODO:填空
    /*
    function renderTicketList(list) {
        console.log(list)
        for (var i = 0; i < list.length; i++) {
            let tempVO = list[i]
            if (tempVO.state == "未完成") {
                continue;
            }
            let state = ""
            let seat = ""
            seat = "<td>" + (tempVO.rowIndex + 1) + "排" + (tempVO.columnIndex + 1) + "座" + "</td>"
            state = "<td>" + tempVO.state + "</td>"
            // console.log("enter")
            // console.log(tempVO)
            getRequest(
                "/schedule/" + tempVO.scheduleId,
                function (res) {
                    console.log("本次排片")
                    console.log(res)
                    let tempSche = res.content
                    // console.log(tempSche.startTime.substring(0,10))
                    let name = "<td>" + tempSche.movieName + "</td>"
                    let hallname = "<td>" + tempSche.hallName + "</td>"
                    let sTime = "<td>" + tempSche.startTime.substring(0, 10) + ' ' + tempSche.startTime.substring(11, 16) + "</td>"
                    let eTime = "<td>" + tempSche.endTime.substring(0, 10) + ' ' + tempSche.endTime.substring(11, 16) + "</td>"
                    let tempStr = "<tr>" + name + hallname + seat + sTime + eTime + state + "</tr>"
                    // console.log("得到每条数据的内容")
                    // console.log(tempStr)
                    $('#ticket-table-body-in').append(tempStr)
                }
            );
            // console.log("lalalalla")
            // console.log(sessionStorage.getItem(count))
        }
    }

     */

    // $(#root-)



});