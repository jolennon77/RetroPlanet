document.addEventListener('DOMContentLoaded', function () {
  // 파일 선택(input) 이벤트 감지
  document.getElementById('profileImg').addEventListener('change', function (event) {
    const fileInput = event.target;
    const previewImg = document.getElementById('previewImg');

    // 선택한 파일이 있는 경우 미리보기 업데이트
    if (fileInput.files && fileInput.files[0]) {
      const reader = new FileReader();
      reader.onload = function (e) {
        previewImg.src = e.target.result;
      };
      reader.readAsDataURL(fileInput.files[0]);
    }
  });
});
