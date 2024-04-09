document.getElementById("styleButton1").addEventListener("click", function() {
  applyStyle("https://unpkg.com/xp.css");
});

document.getElementById("styleButton2").addEventListener("click", function() {
  applyStyle("https://unpkg.com/xp.css/dist/98.css");
});

function applyStyle(styleHref) {
  // 현재 적용된 스타일을 제거합니다.
  var links = document.getElementsByTagName("link");
  for (var i = 0; i < links.length; i++) {
    if (links[i].rel === "stylesheet" && links[i].getAttribute("href") === "/css/basic.css") {
      continue; // 기본 스타일은 건너뜁니다.
    }
    if (links[i].rel === "stylesheet") {
      links[i].parentNode.removeChild(links[i]);
    }
  }

  // 기본 스타일을 다시 적용합니다.
  var defaultStyle = document.createElement("link");
  defaultStyle.rel = "stylesheet";
  defaultStyle.href = "/css/basic2.css";
  document.head.appendChild(defaultStyle);

  // 새로운 스타일을 적용합니다.
  var link = document.createElement("link");
  link.rel = "stylesheet";
  link.href = styleHref;
  document.head.appendChild(link);
}
