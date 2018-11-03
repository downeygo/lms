$(function () {
    var URL = "/device";
    //添加
    $(".add-btn").click(function () {
        showAddModal();
    });

    //删除
    $(".delete-btn").click(function () {
        deleteOne(this,URL);
    });

    //保存
    $(".save-btn").click(function () {
        addOrUpdate(URL);
    });

    //编辑
    $(".edit-btn").click(function () {
        showUpdateModal(this,URL)
    });
});
