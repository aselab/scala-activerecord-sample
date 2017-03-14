$(function() {
  $(".datepicker").each(function() {
    var self = $(this);
    var picker = self.datepicker({
      format: "yyyy-mm-dd",
      autoclose: true,
      todayBtn: true,
      language: self.data("lang")
    });
    var value = self.data("value");
    if (value != null && value != "") {
      picker.datepicker("_setDate", new Date(value));
    }
    $(document).on("click", "#" + self.attr("id") + " .glyphicon-remove", function() {
      picker.find("input").val("").change();
    });
  });
});
