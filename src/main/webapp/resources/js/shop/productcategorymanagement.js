$(function () {

    //后台从session中获取shop的信息，这里不传shopId
    var listUrl = '/o2o/shopadmin/getproductcategorylist';
    var addUrl = '/o2o/shopadmin/addproductcategorys';
    var deleteUrl = '/o2o/shopadmin/removepeoductcategory';

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
                        + '<div class="col-33 product-category-name"> '
                        + item.productCategoryName
                        + '</div>'
                        + '<div class="col-33">'
                        + item.priority
                        + '</div>'
                        + '<div class="col-33"><a href="#" class="button delete" '
                        + item.productCategoryId
                        + '">删除</a></div>'
                        + '</div>';
                });
                $('.product-category-wrap').append(tempHtml);
            }
        });
    }
})