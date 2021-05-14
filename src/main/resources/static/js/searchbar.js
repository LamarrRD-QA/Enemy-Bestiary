(function () {
    const SEARCHINPUT = document.querySelector(`#enemySearch`);
    const SEARCHBUTTON = document.querySelector(`#enemySearchButton`);

    const performSearch = (event) => {
        window.location.href = `/profile.html/?enemyName=${SEARCHINPUT.value}`;
    }

    SEARCHBUTTON.addEventListener(`click`, performSearch);
})();