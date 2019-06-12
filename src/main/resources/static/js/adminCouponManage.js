$(document).ready(function() {
    //选择是否显示root权限
    var role=sessionStorage.getItem('role')
    if (role==='root'){
        $('#root-staff-manage').css("display","")
    }
    
    //得到优惠券列表
    getAllCoupon();

    function getAllCoupon() {
        getRequest(
            '/coupon/giveVIP/get',
            function (res) {
                console.log(res)
            },
            function (err) {
                alert(JSON.stringify(err))
            }
        )
    }


});