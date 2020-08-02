let prepareCategory = function (index, category) {
    let categorySelector = $(category);
    categorySelector.on('click', '*',displayProductsFromCategory)
}

let readyFunction = function () {
    $(".category").each(prepareCategory)
}

$(document).ready(readyFunction)