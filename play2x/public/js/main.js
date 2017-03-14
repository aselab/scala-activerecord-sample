$(document).ready(function(){
  $("#menu li").each(function() {
    if (location.href.indexOf($(this).find("a").attr("href")) >= 0) {
      $(this).addClass("active");
      return false;
    }
  });

  $(".btn-danger").click(function(){
    var result = confirm("Are you sure?");
    var self = $(this);
    if (result) {
      $.ajax({
        url : self.data("target"),
        type: "DELETE",
        data : {
          csrfToken: self.data("token")
        },
        success: function(e) {
          location.href = self.data("redirect");
        }
      });
    }
    return false;
  });
});
