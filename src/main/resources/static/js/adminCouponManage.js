$(document).ready(function() {
    //选择是否显示root权限
    var role=sessionStorage.getItem('role')
    if (role==='root'){
        $('#root-staff-manage').css("display","")
    }


});