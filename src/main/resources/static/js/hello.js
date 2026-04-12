/* Existing greeting function... */
function greeting() {
    alert("Hello Spring Developer!!");
}

// New Function 1: Show Current Time
function showTime() {
    const now = new Date();
    alert("Current Server Time: " + now.toLocaleTimeString());
}

// New Function 2: Toggle Dark Mode
function toggleTheme() {
    const body = document.body;
    if (body.style.backgroundColor === "rgb(51, 51, 51)") {
        body.style.backgroundColor = "white";
        body.style.color = "black";
    } else {
        body.style.backgroundColor = "#333";
        body.style.color = "#f4f4f4";
    }
}
