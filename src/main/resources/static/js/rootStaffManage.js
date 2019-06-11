$(document).ready(function () {

    getAllStaff();

    //得到全部管理员
    function getAllStaff() {
        getRequest(
            '/accountOfManager/get/all',
            function (res) {
                if (res.success){
                    // $('#staff-line').remove()
                    // console.log("before render")
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
            var tline=list[i]
            var link='<button type="button" class="btn btn-primary" data-backdrop="static" data-toggle="modal" data-target="#addStaff" id="editStaff"> 编辑</button> <button type="button" class="btn btn-primary deleteStaffbtn"  id="deleteStaff"> 删除</button>'
            var line='<tr>'+'<td>'+tline.username+'</td>'+'<td>'+tline.password+'</td>'+'<td>'+link+'</td>'+'</tr>'
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
                    if (res.content){
                        alert(res.content)
                    }
                    else {
                        alert("添加成功")
                        getAllStaff()
                    }
                },
                function (err) {
                    alert(err.msg)
                }
            )
        }

        else {
            return
        }

    })
    $("#eidtStaff").click((function (e) {
        console.log(e)

        }
    ))




    function getUserForm() {
        return{
            username:$("#staff-username-input").val(),
            password:$("#staff-password-input").val()
        }
    }

    function validUserForm(user) {
        var isValidUser=true
        console.log(user.username)
        console.log(!user.username)
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