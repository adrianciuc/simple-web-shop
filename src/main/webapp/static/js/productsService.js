const GET_PRODUCTS_OF_CATEGORY_URL = "/category/products"
const ADD_TO_CART_URL = "/cart"

const CATEGORY_ID_PREFIX = "category-"
const PRODUCT_ID_PREFIX = "product-"

let displayProductsFromCategory = function (ev) {
    let url = CONTEXT_PATH + GET_PRODUCTS_OF_CATEGORY_URL
    let rawCategoryId = $(ev.target).parent(".category").attr("id")
    let categoryId = rawCategoryId.replace(CATEGORY_ID_PREFIX, "")
    $.get(url, {"categoryId": categoryId}, handleProductsResponse).fail(displayError)
    $(".category-clicked").removeClass("category-clicked")
    $(ev.target).addClass("category-clicked");
}

let handleProductsResponse = function (data, status) {
    if (status === "success") {
        displayProducts(data)
    }
    if (status === "error") {
        displayError(data)
    }
}

let displayProducts = function(data) {
        $(".products_container").html(data)
        $(".add_to_cart").each(prepareAddToCartButton)
}

let prepareAddToCartButton = function (index, button) {
    $(button).click(addToCart)
}

let addToCart = function (ev) {
    let rawProductId = $(ev.target).closest(".product_container").attr("id");
    let productId = rawProductId.replace(PRODUCT_ID_PREFIX, "")
    let url = CONTEXT_PATH + ADD_TO_CART_URL
    $.post(url, {"productId": productId}, handleAddToCartResponse).fail(displayError)
}

let handleAddToCartResponse = function (data, status) {
    let nrProductsInCartContainer = $("#nr_products_in_cart");
    let currentCounter = nrProductsInCartContainer.html()
    nrProductsInCartContainer.html(++currentCounter);
}