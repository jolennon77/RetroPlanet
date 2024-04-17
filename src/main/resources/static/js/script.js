// document.querySelectorAll('menu[role="tablist"] button').forEach(button => {
//   button.addEventListener('click', function() {
//     const selectedTab = this.getAttribute('aria-controls');
//     document.querySelectorAll('.window-body article').forEach(article => {
//       if (article.id === selectedTab) {
//         article.hidden = false;
//       } else {
//         article.hidden = true;
//       }
//     });
//   });
//   button.addEventListener('click', function() {
//     // 모든 버튼에서 aria-selected 속성을 false로 설정
//     document.querySelectorAll('button[aria-controls]').forEach(btn => {
//       btn.setAttribute('aria-selected', 'false');
//     });
//
//     // 클릭된 버튼에만 aria-selected 속성을 true로 설정
//     this.setAttribute('aria-selected', 'true');
//   });
// });
//
//
//
// document.querySelectorAll('menu[role="header_tablist"] button').forEach(button => {
//   button.addEventListener('click', function() {
//     const selectedTab = this.getAttribute('aria-controls');
//     document.querySelectorAll('.window-body article').forEach(article => {
//       if (article.id === selectedTab) {
//         article.hidden = false;
//       } else {
//         article.hidden = true;
//       }
//     });
//   });
//   button.addEventListener('click', function() {
//     // 모든 버튼에서 aria-selected 속성을 false로 설정
//     document.querySelectorAll('button[aria-controls]').forEach(btn => {
//       btn.setAttribute('aria-selected', 'false');
//     });
//
//     // 클릭된 버튼에만 aria-selected 속성을 true로 설정
//     this.setAttribute('aria-selected', 'true');
//   });
// });

// 상단 탭
/*
document.querySelectorAll('.header menu[role="tablist"] button').forEach(button => {
  button.addEventListener('click', function() {
    const selectedTab = this.getAttribute('aria-controls');
    document.querySelectorAll('.header .window-body article').forEach(article => {
      if (article.id === selectedTab) {
        article.hidden = false;
      } else {
        article.hidden = true;
      }
    });
  });
  button.addEventListener('click', function() {
    // 모든 버튼에서 aria-selected 속성을 false로 설정
    document.querySelectorAll('.header button[role="tab"]').forEach(btn => {
      btn.setAttribute('aria-selected', 'false');
    });

    // 클릭된 버튼에만 aria-selected 속성을 true로 설정
    this.setAttribute('aria-selected', 'true');
  });
});
*/

// 하단 탭
document.querySelectorAll('.center menu[role="tablist"] button').forEach(button => {
  button.addEventListener('click', function() {
    const selectedTab = this.getAttribute('aria-controls');
    document.querySelectorAll('.center .window-body article').forEach(article => {
      if (article.id === selectedTab) {
        article.hidden = false;
      } else {
        article.hidden = true;
      }
    });
  });

  button.addEventListener('click', function() {
    // 모든 버튼에서 aria-selected 속성을 false로 설정
    document.querySelectorAll('.center button[aria-controls]').forEach(btn => {
      btn.setAttribute('aria-selected', 'false');
    });

    // 클릭된 버튼에만 aria-selected 속성을 true로 설정
    this.setAttribute('aria-selected', 'true');
  });
});
