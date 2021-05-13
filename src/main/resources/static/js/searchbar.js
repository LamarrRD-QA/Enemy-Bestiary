(function () {
    const SEARCHINPUT = document.querySelector(`#enemySearch`);
    const SEARCHBUTTON = document.querySelector(`#enemySearchButton`);
    const BASEURL = `http://localhost:8080`;

    const performSearch = (event) => {
        window.location.href = `${BASEURL}/profile.html/?enemyName=${SEARCHINPUT.value}`;
    }

    SEARCHBUTTON.addEventListener(`click`, performSearch);
})();