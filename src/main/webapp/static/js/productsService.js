const GET_PRODUCTS_OF_CATEGORY_URL = "/category/products"
const CATEGORY_ID_PREFIX = "category-"

let displayProductsFromCategory = function (ev) {
    let url = CONTEXT_PATH + GET_PRODUCTS_OF_CATEGORY_URL
    let categoryId = ev.target.id.replace(CATEGORY_ID_PREFIX, "")

    $.get(url, {"categoryId": categoryId}, function(data, status) {
        if (status === "success") {
            $(".products_container").html(data)
        }
    })
}