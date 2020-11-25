$(".delete").each(function () {
  var $this = $(this)
  $this.on("submit", function (e) {
    e.preventDefault()
    confirmDialog("Are you sure you want to delete this post?", (ans) => {
      if (ans) {
        $(this).unbind()
        $(this).submit()
      } else {
        $('#confirmModal').modal('hide');
      }
    })
  })
})

function confirmDialog(message, handler) {
  $(`<div class="modal fade" id="confirmModal" role="dialog"> 
     <div class="modal-dialog modal-sm"> 
        <div class="modal-content">
           <div class="modal-body"> 
             <h4 class="text-center mb-3">${message}</h4> 
             <div class="text-center"> 
              <button type="button" class="btn btn-danger btn-yes">Yes</button>
              <button type="button" class="btn btn-secondary btn-no">No</button>
             </div> 
           </div> 
       </div> 
    </div> 
  </div>`).appendTo("body")

  //Trigger the modal
  $("#confirmModal").modal({
    backdrop: "static",
    keyboard: false,
  })

  //Pass true to a callback function
  $(".btn-yes").click(function () {
    handler(true)
    $("#confirmModal").modal("hide")
  })

  //Pass false to callback function
  $(".btn-no").click(function () {
    handler(false)
    $("#confirmModal").modal("hide")
  })

  //Remove the modal once it is closed.
  $("#confirmModal").on("hidden.bs.modal", function () {
    $("#confirmModal").remove()
  })
}
