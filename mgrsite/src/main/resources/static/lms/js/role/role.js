$(function () {
    var URL = "/role";
    var selectURL = "/role_getPermission";
    //添加
    $(".add-btn").click(function () {
        showAddModal(selectURL);
    });

    //编辑
    $(".edit-btn").click(function () {
        showUpdateModal(this, URL, "permissions", selectURL);
    });

    //保存
    $(".save-btn").click(function () {
        addOrUpdate(URL);
    });

    //删除
    $(".delete-btn").click(function () {
        deleteOne(this, URL)
    });
});