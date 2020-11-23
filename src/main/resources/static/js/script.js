document.querySelectorAll(".delete").forEach((btn) => {
  btn.onclick = () => {
    if (confirm("Are you sure?")) {
      return true;
    } else {
      return false;
    }
  }
})