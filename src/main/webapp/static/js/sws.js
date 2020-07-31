let prepareCategory = function (index, category) {
    $(category).click(displayProductsFromCategory)
}

let readyFunction = function () {
    $(".category").each(prepareCategory)
}

$(document).ready(readyFunction)