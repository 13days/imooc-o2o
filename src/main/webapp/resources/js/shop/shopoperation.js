/*
JS主要负责两个功能
1、从后台获取到店铺分类以及区域等信息填充至前台的html
2、把前台的数据传到后台
 */
$(function () {
    var shopId = getQueryString('shopId');
    var isEdit = shopId ? true : false;  //如果是true 则是修改店铺信息 如果是false 则是注册店铺（前端页面复用）
    var initUrl = '/o2o/shop/getshopinitinfo'; //后台传递数据给前台
    var registerShopUrl = '/o2o/shop/registershop';    //前台把填写的数据获取到后台
    var shopInfoUrl = '/o2o/shop/getshopbyid?shopId=' + shopId;
    var editShopUrl = '/o2o/shop/modifyshop';

    //alert(initUrl);  //Test
    // getShopInitInfo();
    if (!isEdit) {
        getShopInitInfo();
    } else {
        getShopInfo(shopId);
    }

    function getShopInfo(shopId) {
        $.getJSON(shopInfoUrl, function (data) {
            if (data.success) {
                var shop = data.shop;
                $('#shop-name').val(shop.shopName);
                $('#shop-addr').val(shop.shopAddr);
                $('#shop-desc').val(shop.shopDesc);
                $('#shop-phone').val(shop.phone);
                var shopCategory = '<option data-id="' + shop.shopCategory.shopCategoryId + '"selected>'
                    + shop.shopCategory.shopCategoryName + '</option>';

                var tempAreaHtml = '';
                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">'
                        + item.areaName + '</option>';
                });
                $('#shop-category').html(shopCategory);
                $('#shop-category').attr('disabled', 'disabled');
                $('#area').html(tempAreaHtml);
                $("#area option[data-id='" + shop.area.areaId + "']").attr("selected", "selected");
            }
        });
    }

    //shopManagermentController.java
    function getShopInitInfo() {

        $.getJSON(initUrl, function (data) {
            if (data.success) {
                var tempHtml = '';
                var tempAreaHtml = '';

                //从后台获取ShopCategory的option内容并遍历
                data.shopCategoryList.map(function (item, index) {
                    tempHtml += '<option data-id="' + item.shopCategoryId + '">'
                        + item.shopCategoryName + '</option>'
                });

                //从后台获取区域信息并遍历
                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">'
                        + item.areaName + '</option>';
                });

                //将信息放进前台html中
                $('#shop-category').html(tempHtml);
                $('#area').html(tempAreaHtml);
            }
        });
    }

    //前台提交按钮事件
    $('#submit').click(function () {
        var shop = {};
        if (isEdit) {
            shop.shopId = shopId;
        }
        shop.shopName = $('#shop-name').val();
        shop.shopAddr = $('#shop-addr').val();
        shop.phone = $('#shop-phone').val();
        shop.shopDesc = $('#shop-desc').val();
        shop.shopCategory = {
            shopCategoryId: $('#shop-category').find('option').not(function () {
                return !this.selected;
            }).data('id')
        };

        shop.area = {
            areaId: $('#area').find('option').not(function () {
                return !this.selected;
            }).data('id')
        };

        var shopImg = $('#shop-img')[0].files[0];
        var formData = new FormData();
        formData.append('shopImg', shopImg);
        formData.append('shopStr', JSON.stringify(shop));


        var verifyCodeActual = $('#j_captcha').val();
        if (!verifyCodeActual) {
            $.toast("请输入验证码！");
            return;
        }
        //alert(verifyCodeActual);
        formData.append('verifyCodeActual', verifyCodeActual);

        $.ajax({
            url: (isEdit ? editShopUrl : registerShopUrl),
            // url:registerShopUrl,
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            success: function (data) {
                if (data.success) {
                    $.toast('提交成功！');
                } else {
                    $.toast('提交失败！' + data.errMsg);
                    $('#captcha_img').click();
                }
            }
        });
    });


});