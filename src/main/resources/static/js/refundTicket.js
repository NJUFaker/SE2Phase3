
$(document).ready(function () {
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
        }
    }

    $("#activity-form-btn").click(function () {
        var ticket=getrefundStrategy();
        console.log(ticket)
        if (validUserForm(ticket)){
            postRequest(
                '/refund/add',
                ticket
                ,
                function (res) {
                    console.log(res)
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
                })
        }
        else {
            return;
        }
    })


    
})