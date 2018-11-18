$(function () {

    //后台从session中获取shop的信息，这里不传shopId
    var listUrl = '/o2o/shopadmin/getproductcategorylist';
    var addUrl = '/o2o/shopadmin/addproductcategorys';
    var deleteUrl = '/o2o/shopadmin/removeproductcategory';

    // 调用getProductCategoryList，加载数据
    getProductCategoryList();

    function getProductCategoryList() {
        $.getJSON(listUrl, function (data) {
            if (data.success) {
                var dataList = data.data;
                $('.product-categroy-wrap').html('');
                var tempHtml = '';
                dataList.map(function (item, index) {
                    tempHtml += ''
                        + '<div class="row row-product-category now">'
                        + '<div class="col-33 product-category-name">'
                        + item.productCategoryName
                        + '</div>'
                        + '<div class="col-33">'
                        + item.priority
                        + '</div>'
                        + '<div class="col-33"><a href="#" class="button delete" data-id=" '
                        + item.productCategoryId
                        + '">删除</a></div>'
                        + '</div>';
                });
                $('.product-category-wrap').append(tempHtml);
            }
        });
    }


    //定义新增按钮事件  每点击一次都会出现一个新的div
    $('#new').click(function () {
        var tempHtml = '<div class="row row-product-category temp">'
            + '<div class="col-33"><input class="category-input category" type="text" placeholder="分类名"></div>'
            + '<div class="col-33"><input class="category-input priority" type="number" placeholder="优先级"></div>'
            + '<div class="col-33"><a href="#" class="button delete">删除</a></div>'
            + '</div>';

        $('.product-category-wrap').append(tempHtml);
    });

    $('#submit').click(function () {
        //通过temp获取新增的行
        var tempArr = $('.temp');
        // 定义数组接收新增的数据
        var productCategoryList = [];
        tempArr.map(function (index,item){
            var tempObj = {};
            tempObj.productCategoryName = $(item).find('.category').val();
            tempObj.priority = $(item).find('.priority').val();
            if(tempObj.productCategoryName && tempObj.priority){
                productCategoryList.push(tempObj);
            }
        });

        //Ajax
        $.ajax({
            url:addUrl,
            type:'POST',
            // 后端通过 @HttpRequestBody直接接收
            data:JSON.stringify(productCategoryList),
            contentType:'application/json',
            success:function (data) {
                if(data.success){
                    $.toast('新增信息成功');
                    //重新加载数据
                    getProductCategoryList();
                    //调用上面方法之后刷新此页面
                    location.reload()
                }else{
                    $.toast('提交失败--'+data.errMsg);
                }
            }
        });
    });


    // 一种是需要提交到后台的删除now  ，另外一种是新增但未提交到数据库中的删除temp
    //temp
    $('.product-category-wrap').on('click','.row-product-category.temp .delete',function (e) {
        /*console.log($(this).parent().parent());*/
        $(this).parent().parent().remove();
    })

    //now
    $('.product-category-wrap').on('click','.row-product-category.now .delete',function (e) {
        var target = e.currentTarget;
        $.confirm("确定删除?",function () {
            $.ajax({
                url:deleteUrl,
                type:'POST',
                data:{
                    productCategoryId : target.dataset.id,
                },
                dataType:'json',
                success:function (data) {
                    if(data.success){
                        $.toast('删除成功√');
                        //重新加载数据
                        getProductCategoryList();
                        //调用上面方法之后刷新此页面
                        location.reload()
                    }else{
                        $.toast('删除失败x');
                    }
                }
            });
        });
    });
});