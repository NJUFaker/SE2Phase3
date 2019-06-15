$(document).ready(function() {
    //选择是否显示root权限
    var role=sessionStorage.getItem('role')
    console.log(role)
    console.log(role==='root')
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
                if (res.success){
                    var couponList = res.content;
                    var couponListContent = '';
                    for (let coupon of couponList) {
                        couponListContent += '<div class="col-md-4 coupon-wrapper"><input class="coupon-checkbox col-md-1 left" type="checkbox" name="select-coupon" id="couponid+' +
                            coupon.couponId +
                            '" value="' +coupon.couponId+
                            '" ><label class="coupon-label col-md-11 right" for="couponid+' +coupon.couponId+
                            '">' +
                            '<div class="coupon"><div class="content">' +
                            '<div class="col-md-8 left">' +
                            '<div class="name">' +
                            coupon.name +
                            '</div>' +
                            '<div class="description">' +
                            coupon.description +
                            '</div>' +
                            '<div class="price">' +
                            '满' + coupon.targetAmount + '减' + coupon.discountAmount +
                            '</div>' +
                            '</div>' +
                            '<div class="col-md-5 right">' +
                            '<div>有效日期</div>' +
                            '<div>' + formatDate(coupon.startTime) + ' ~ ' + formatDate(coupon.endTime) + '</div>' +
                            '</div></div></div></label></input></div>'
                    }

                    $('#all-coupon-list').append(couponListContent);
                }
            },
            function (err) {
                alert(JSON.stringify(err))
            }
        )
    }
    function formatDate(date) {
        return date.substring(5, 10).replace("-", ".");
    }
});


//发放优惠券
function submitPro() {
    var couponId=getCouponIdList()
    var amount=$('.vip-consume-input').val()
    // console.log(amount)
    // console.log(couponId)
    // console.log("start post*3")

    postRequest(
        '/coupon/giveVIP/give?consume='+amount+'&couponIds='+couponId,
        couponId,
        function (res) {
            console.log(res)
            if (res.success){
                alert("消费额满"+amount+"的会员获赠"+couponId.length+'张优惠券')
            }
            else {
                alert(res.message)
            }
        },
        function (err) {
            alert("网络错误")
        }

    )

}
//得到被选中的优惠券
function getCouponIdList () {
    var list=$("input[name='select-coupon']:checked")
    var couponId=[]
    // console.log(list)
    if (list.length==0){
        alert("请选择要赠送的优惠券")
    }
    else {
    list.each(function(){
        var cur=$(this).val()
        // console.log(cur)
        // // console.log("*******************************")
        couponId.push(cur)
    })
    return couponId
    }
}
