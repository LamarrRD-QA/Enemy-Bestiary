'use strict';

(function () {
    const BASEURL = `http://localhost:8080`;
    const TABLEBODY = document.querySelector(`#enemyTableBody`);
    const ADDENEMYSUBMIT = document.querySelector(`#addEnemySubmit`);
    const LOCATIONS = document.querySelector(`#addEnemyLocations`);
    // const ADDENEMYMODAL = document.querySelector(`#addEnemyModal`);

    const makeGetRequest = async () => {
        try {
            const response = await axios.get(`${BASEURL}/catalogue`);
            const enemies = response.data;
            console.log(`GET: Retrieved list of enemies`, enemies);
            enemies.forEach(enemy => createEnemyTableRow(enemy));
        } catch (error) {
            console.error(error);
        }
    };

    const handleSelectedToArray = () => {
        let testArr = [];
        let options = LOCATIONS.childNodes;

        for (const option of options) {
            if (option.selected) {
                testArr.push(option.textContent);
            }
        }

        return testArr;
    }

    const handleFormSubmit = (event) => {
        event.preventDefault();
        console.log(document.forms.length);

        const formData = new FormData(document.forms.namedItem(`addEnemyForm`));

        // TODO: Implement image upload functionality for custom enemy
        // const formImage = new FormData();
        // formImage.append(`image`, formData.get(`image`));
        // formData.delete(`image`);
        // sendImageToServer(formImage);

        const formJSON = manipulateData(formData);
        console.log(formJSON);


        sendDataToSQL(formJSON);
    }

    const manipulateData = (data) => {

        // Create list of selected options
        let locationArr = [];
        for (const option of LOCATIONS.childNodes) {
            if (option.selected) {
                locationArr.push(option.textContent);
            }
        }

        console.log(locationArr);

        // Create JSON of form data and correct data types;
        let newJSON = Object.fromEntries(data.entries());
        newJSON.locations = locationArr;
        newJSON.isCustom = true;

        newJSON.level = parseInt(newJSON.level);
        newJSON.maxHP = parseInt(newJSON.maxHP);
        newJSON.pow = parseInt(newJSON.pow);
        newJSON.def = parseInt(newJSON.def);
        newJSON.speed = parseInt(newJSON.speed);
        newJSON.exp = parseInt(newJSON.exp);
        newJSON.coins = parseInt(newJSON.coins);

        return newJSON;
    }

    // TODO: Implement image upload functionality for custom enemy
    // const sendImageToServer = async (image) => {
    //     try {
    //         const response = await axios.post(`/catalogue/uploadImg`, image, {
    //             headers: {
    //                 'Content-Type': 'multipart/form-data'
    //             }
    //         });
    //         let responseImg = response.data;
    //         console.log(`POST: Uploaded image of enemy`, responseImg);
    //     } catch (error) {
    //         console.error(error);
    //     }
    // }

    const sendDataToSQL = async (enemyData) => {
        try {
            const response = await axios.post(`/catalogue`, enemyData);
            let enemy = response.data;
            console.log(`POST: Created enemy`, enemy);
            createEnemyTableRow(enemy);
        } catch (error) {
            console.error(error);
        }
    }

    const setIconForEnemyTable = (enemy) => {
        let iconText = ``;
        if (enemy.enemyType === `Normal`) {
            iconText = `bi-square-half`;
        } else if (enemy.enemyType === `Boss`) {
            iconText = `bi-diamond-half`;
        } else if (enemy.enemyType === `Support`) {
            iconText = `bi-heart-half`;
        }
        return iconText;
    }

    const createEnemyTableRow = (enemy) => {

        let row = document.createElement(`tr`);
        
        // Create node for enemy image and append to row;
        let columnImg = document.createElement(`td`);
        let enemyImg = undefined;
        if (enemy.isCustom) {
            enemyImg = document.createElement(`i`);
            enemyImg.setAttribute(`class`, `bi ${setIconForEnemyTable(enemy)}`)
        } else {
            enemyImg = document.createElement(`img`);
            enemyImg.setAttribute(`src`, `../res/normal_enemy/${enemy.name}.png`);
            enemyImg.setAttribute(`alt`, `Image of ${enemy.name}`);
        }
        columnImg.appendChild(enemyImg);
        row.appendChild(columnImg);

        // Create node for enemy name and append to row
        let enemyName = document.createTextNode(enemy.name);
        let columnName = document.createElement(`td`);
        columnName.appendChild(enemyName);
        row.appendChild(columnName);

        // Create node for enemy type and append to row
        let enemyType = document.createTextNode(enemy.enemyType);
        let columnType = document.createElement(`td`);
        columnType.appendChild(enemyType);
        row.appendChild(columnType);

        // Create node for whether enemy is normal or custom and append to row
        let enemyIsCustom = enemy.isCustom ? document.createTextNode(`Custom`) : document.createTextNode(`Normal`);
        let columnIsCustom = document.createElement(`td`);
        columnIsCustom.appendChild(enemyIsCustom);
        row.appendChild(columnIsCustom);

        TABLEBODY.appendChild(row);
    }

    makeGetRequest();
    LOCATIONS.addEventListener(`change`, handleSelectedToArray);
    ADDENEMYSUBMIT.addEventListener(`click`, handleFormSubmit);

})();
