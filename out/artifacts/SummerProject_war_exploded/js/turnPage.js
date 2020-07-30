let pages = document.getElementsByClassName("pages");
let btns = document.getElementsByClassName("btns");
let pageNum = pages.length;
let preBtn = document.getElementById("pre");
let nextBtn = document.getElementById("next");

preBtn.onclick = function () {
    if(pages[0].style.display != "none") {
        alert("It's the first page");
    } else {
        for(let i = 0; i < pageNum; i++) {
            if(pages[i].style.display != "none") {
                pages[i].style.display = "none";
                pages[i - 1].style.display = "";
                btns[i].style.color = "black";
                btns[i - 1].style.color = "blue";
                break;
            }
        }
    }
};

nextBtn.onclick = function () {
    if(pages[pageNum - 1].style.display != "none") {
        alert("It's the last page");
    } else {
        for(let i = 0; i < pages.length; i++) {
            if(pages[i].style.display != "none") {
                pages[i].style.display = "none";
                pages[i + 1].style.display = "block";
                btns[i].style.color = "black";
                btns[i + 1].style.color = "blue";
                break;
            }
        }
    }
};

for(let i = 0; i < pageNum; i++) {
    btns[i].onclick = function () {
        for(let j = 0; j < pageNum; j++) {
            pages[j].style.display = "none";
            btns[j].style.color = "black";
        }
        pages[i].style.display = "block";
        btns[i].style.color = "blue";
    }
}