$(function () {
    $('#find').on('click', function like() {
        var value = $('#value').val();
        console.log(value);
        $.ajax({
            type: 'get',
            url: "/manager/personnel_management/find",
            dataType: "json",
            data: {"value": msg},
            success: function (data, status) {
                console.log(data);
            },
            error: function (err) {
                console.log(err);
            }
        });
    });
});