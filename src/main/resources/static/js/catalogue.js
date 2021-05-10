'use strict';

(function () {
    const BASE_URL = `http://localhost:8080`;
    const TABLEBODY = document.querySelector(`#enemyTableBody`);

    const makeGetRequest = async () => {
        try {
            const response = await axios.get(`${BASE_URL}/catalogue`);
            const enemies = response.data;
            console.log(`GET: Retrieved list of enemies`, enemies);
            enemies.forEach(enemy => createEnemyTableRow(enemy));
        } catch (error) {
            console.error(error);
        }
    };

    const createEnemyTableRow = (enemy) => {
        let row = document.createElement(`tr`);

        // Create node for enemy image and append to row
        let enemyImg = document.createElement(`img`);
        enemyImg.setAttribute(`src`, `../res/table_enemy/${enemy.name}.png`);
        enemyImg.setAttribute(`alt`, `Image of ${enemy.name}`);
        let columnImg = document.createElement(`th`);
        columnImg.setAttribute(`scope`, `col`);
        columnImg.appendChild(enemyImg);
        row.appendChild(columnImg);

        // Create node for enemy name and append to row
        let enemyName = document.createTextNode(enemy.name);
        let columnName = document.createElement(`th`);
        columnName.setAttribute(`scope`, `col`);
        columnName.appendChild(enemyName);
        row.appendChild(columnName);

        // Create node for enemy type and append to row
        let enemyType = document.createTextNode(enemy.enemyType);
        let columnType = document.createElement(`th`);
        columnType.setAttribute(`scope`, `col`);
        columnType.appendChild(enemyType);
        row.appendChild(columnType);

        // Create node for whether enemy is normal or custom and append to row
        let enemyIsCustom = enemy.isCustom ? document.createTextNode(`Custom`) : document.createTextNode(`Normal`);
        let columnIsCustom = document.createElement(`th`);
        columnIsCustom.setAttribute(`scope`, `col`);
        columnIsCustom.appendChild(enemyIsCustom);
        row.appendChild(columnIsCustom);

        TABLEBODY.appendChild(row);
    }

    makeGetRequest();
})();
