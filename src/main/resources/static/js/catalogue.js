'use strict';

(function () {
    // const BASEURL = `http://localhost:8080`;
    const TABLEBODY = document.querySelector(`#enemyTableBody`);
    const ADDENEMYSUBMIT = document.querySelector(`#addEnemySubmit`);
    const ADDENEMYLOCATIONS = document.querySelector(`#addEnemyLocations`);
    const UPDATEENEMYSUBMIT = document.querySelector(`#updateEnemySubmit`);
    const DELETEENEMYBUTTON = document.querySelector(`#deleteEnemyYes`);
    const UPDATEENEMYLOCATIONS = document.querySelector(`#updateEnemyLocations`);
    const UPDATEENEMYMODAL = document.querySelector(`#updateEnemyModal`);
    const DELETEENEMYMODAL = document.querySelector(`#deleteEnemyModal`);
    const ADDENEMYMODAL = document.querySelector(`#addEnemyModal`);
    const CRUDNOTICE = document.querySelector(`#notif`);


    const makeGetRequest = async () => {
        try {
            const response = await axios.get(`/catalogue`);
            const enemies = response.data;
            if (Object.entries(enemies).length === 0) {
                displayGetError();
            } else {
                console.log(`GET: Retrieved list of enemies`, enemies);
                enemies.forEach(enemy => createEnemyTableRow(enemy));
            }
        } catch (error) {
            console.error(error);
        }
    };

    const displayGetError = () => {
        let enemyTable = document.querySelector(`#enemyTable`);
        enemyTable.style.visibility = "hidden";

        let errorMessage = document.createElement(`div`);
        let errorMessageText = document.createTextNode(`Sorry, no enemies were found to display!`);
        errorMessage.setAttribute(`class`, `alert alert-danger`);
        errorMessage.setAttribute(`role`, `alert`);
        errorMessage.setAttribute(`id`, `listEnemyNotFoundAlert`);
        errorMessage.appendChild(errorMessageText);
        document.body.appendChild(errorMessage);
    }

    const handleSelectedToArray = (event) => {
        let testArr = [];
        let options = event.currentTarget.options;

        for (const option of options) {
            if (option.selected) {
                testArr.push(option.textContent);
            }
        }

        return testArr;
    }

    const handleAddFormSubmit = (event) => {
        event.preventDefault();

        const formData = new FormData(document.forms.namedItem(`addEnemyForm`));

        // TODO: Implement image upload functionality for custom enemy
        // const formImage = new FormData();
        // formImage.append(`image`, formData.get(`image`));
        // formData.delete(`image`);
        // sendImageToServer(formImage);

        const formJSON = manipulateData(formData);
        postDataToSQL(formJSON);
        
    }

    const handleUpdateFormSubmit = (event) => {
        event.preventDefault();

        const formData = new FormData(document.forms.namedItem(`updateEnemyForm`));


        // TODO: Implement image upload functionality for custom enemy
        // const formImage = new FormData();
        // formImage.append(`image`, formData.get(`image`));
        // formData.delete(`image`);
        // sendImageToServer(formImage);

        const formJSON = manipulateData(formData);
        putDataToSQL(formJSON, formJSON.id);
    }

    const manipulateData = (data) => {

        let locationNode = undefined;

        if (document.querySelector(`#addEnemyModal`).classList.contains(`show`)) {
            locationNode = document.querySelector(`#addEnemyLocations`);
        } else if (document.querySelector(`#updateEnemyModal`).classList.contains(`show`)) {
            locationNode = document.querySelector(`#updateEnemyLocations`);
        }

        // Create list of selected options
        let locationArr = [];
        for (const option of locationNode.childNodes) {
            if (option.selected) {
                locationArr.push(option.textContent);
            }
        }

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

    const postDataToSQL = async (enemyData) => {
        try {
            const response = await axios.post(`/catalogue`, enemyData);
            let enemy = response.data;
            console.log(`POST: Created enemy`, enemy);
            
            createEnemyTableRow(enemy);
            updateNotification(`Congrats! Your enemy has been created.`);

            let modal = bootstrap.Modal.getInstance(ADDENEMYMODAL);
            await modal.hide();

            let toast = new bootstrap.Toast(CRUDNOTICE, {
                delay: 800
            });
            await toast.show();
        } catch (error) {
            console.error(error);
        }
    }

    const putDataToSQL = async (enemyData, id) => {
        try {
            const response = await axios.put(`/catalogue/${id}`, enemyData);
            let enemy = response.data;
            console.log(`PUT: Updated enemy`, enemy);
            
            updateEnemyTableRow(enemy);
            updateNotification(`Congrats! Enemy ${id} has been updated.`);

            let modal = bootstrap.Modal.getInstance(UPDATEENEMYMODAL);
            await modal.hide();

            let toast = new bootstrap.Toast(CRUDNOTICE, {
                delay: 800
            });
            await toast.show();
        } catch (error) {
            console.error(error);
        }
    }

    const updateNotification = (message) => {
        let notif = document.getElementById(`notif`);

        notif.firstElementChild.nextElementSibling.innerHTML = message;
    } 

    const updateEnemyTableRow = (enemy) => {
        let currentRow = document.querySelector(`#tableRow${enemy.id}`);
        currentRow.cells[0].innerHTML = `<i class=bi ${setIconForEnemyTable(enemy)}></i>`;
        currentRow.cells[1].innerHTML = enemy.name;
        currentRow.cells[2].innerHTML = enemy.enemyType;
        //currentRow.cells[3].innerHTML = enemy.isCustom ? `Custom` : `Normal`;
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

    const managePopover = (row) => {
        let rowPopOver = new bootstrap.Popover(row, {
            container: row,
            placement: `bottom`,
            trigger: `focus`,
            html: true,
            sanitize: false,
            content: `<button type="button" id="btnView" class="btn btn-secondary" data-enemyid=${row.dataset["enemyid"]}>View</button> ` +
                `<button type="button" id="btnUpdate" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#updateEnemyModal" data-enemyid=${row.dataset["enemyid"]}>Update</button> ` +
                `<button type="button" id="btnDelete" class="btn btn-danger" data-enemyid=${row.dataset["enemyid"]} data-bs-toggle="modal" data-bs-target="#deleteEnemyModal">Delete</button> `
        });
    }

    const viewEnemyPage = (event) => {

        window.location.href = `/profile.html/?id=${event.target.dataset["enemyid"]}`;
    }

    const findEnemyByID = async (id) => {
        try {
            const response = await axios.get(`/catalogue/${id}`);
            let enemy = response.data;
            console.log(`GET: Found enemy`, enemy);
            updateEnemyModal(enemy);
        } catch (error) {
            console.error(error);
        }
    }

    const setupFindEnemy = (event) => {
        let enemyID = event.relatedTarget.dataset["enemyid"];
        findEnemyByID(enemyID);
    }

    const updateEnemyModal = (enemy) => {
        // Obtain values from enemy JSON and display in form
        document.getElementById(`updateEnemyID`).value = enemy.id;
        document.getElementById(`updateEnemyName`).value = enemy.name;
        document.getElementById(`updateEnemyType`).value = enemy.enemyType;
        document.getElementById(`updateEnemyLevel`).value = enemy.level;
        document.getElementById(`updateEnemyCoins`).value = enemy.coins;
        document.getElementById(`updateEnemyExp`).value = enemy.exp;
        document.getElementById(`updateEnemyHP`).value = enemy.maxHP;
        document.getElementById(`updateEnemyPow`).value = enemy.pow;
        document.getElementById(`updateEnemyDef`).value = enemy.def;
        document.getElementById(`updateEnemySpeed`).value = enemy.speed;
        document.getElementById(`updateEnemyLevel`).value = enemy.level;
        document.getElementById(`updateEnemyStun`).value = enemy.chanceOfStun;
        document.getElementById(`updateEnemyBurn`).value = enemy.chanceOfBurn;
        document.getElementById(`updateEnemyStatDown`).value = enemy.chanceOfStatDown;
        document.getElementById(`updateEnemyJumpReaction`).value = enemy.jump;
        document.getElementById(`updateEnemyHammerReaction`).value = enemy.hammer;
        document.getElementById(`updateEnemyHandReaction`).value = enemy.hand;
        document.getElementById(`updateEnemyFireReaction`).value = enemy.fire;
        document.getElementById(`updateEnemyThunderReaction`).value = enemy.thunder;
        document.getElementById(`updateEnemyItemOne`).value = enemy.itemOne;
        document.getElementById(`updateEnemyChanceItemOne`).value = enemy.itemOneChance;
        document.getElementById(`updateEnemyItemTwo`).value = enemy.itemTwo;
        document.getElementById(`updateEnemyChanceItemTwo`).value = enemy.itemTwoChance;


        // Select multiple options based on content of enemy.locations in JSON
        let locations = document.getElementById(`updateEnemyLocations`);

        for (let index = 0; index < locations.options.length; index++) {
            let option = locations.options[index];

            if (enemy.locations.indexOf(option.text) != -1) {
                option.selected = true;
            }

        }

    }

    const setupDeleteEnemyModal = (event) => {
        let enemyID = event.relatedTarget.dataset["enemyid"];
        DELETEENEMYBUTTON.dataset["enemyid"] = enemyID;
    }

    const handleDeleteEnemy = (event) => {
        let enemyID = event.target.dataset["enemyid"];
        DELETEENEMYMODAL.classList.remove(`show`);
        deleteEnemyFromSQL(enemyID);
    }

    const deleteEnemyFromSQL = async (id) => {
        try {
            await axios.delete(`/catalogue/${id}`);
            console.log(`DELETE: Enemy successful deleted`);

            removeTableRow(id);
            updateNotification(`Congrats! Enemy ${id} has been deleted.`)

            let modal = bootstrap.Modal.getInstance(DELETEENEMYMODAL);
            await modal.hide();

            let toast = new bootstrap.Toast(CRUDNOTICE, {
                delay: 800
            });
            await toast.show();
        } catch (error) {
            console.error(error);
        }
    }

    const removeTableRow = (id) => {
        let currentRow = document.querySelector(`#tableRow${id}`);
        currentRow.remove();
    }

    const createEnemyTableRow = (enemy) => {
        //Set needed attributes for new table row
        let row = document.createElement(`tr`);
        row.setAttribute(`tabindex`, `-1`);
        row.setAttribute(`data-enemyid`, enemy.id);
        row.setAttribute(`id`, `tableRow${enemy.id}`);


        // Create node for enemy image and append to row;
        let imgCell = document.createElement(`td`);
        let enemyImg = undefined;
        if (enemy.isCustom) {
            enemyImg = document.createElement(`i`);
            enemyImg.setAttribute(`class`, `bi ${setIconForEnemyTable(enemy)}`)
        } else {
            enemyImg = document.createElement(`img`);
            enemyImg.setAttribute(`src`, `../res/normal_enemy_img/${enemy.name}.png`);
            enemyImg.setAttribute(`alt`, `Image of ${enemy.name}`);
        }
        imgCell.appendChild(enemyImg);
        row.appendChild(imgCell);

        // Create node for enemy name and append to row
        let enemyName = document.createTextNode(enemy.name);
        let nameCell = document.createElement(`td`);
        nameCell.appendChild(enemyName);
        row.appendChild(nameCell);

        // Create node for enemy type and append to row
        let enemyType = document.createTextNode(enemy.enemyType);
        let enemyTypeCell = document.createElement(`td`);
        enemyTypeCell.appendChild(enemyType);
        row.appendChild(enemyTypeCell);

        // Create node for whether enemy is normal or custom and append to row
        let enemyIsCustom = enemy.isCustom ? document.createTextNode(`Custom`) : document.createTextNode(`Normal`);
        let enemyIsCustomCell = document.createElement(`td`);
        enemyIsCustomCell.appendChild(enemyIsCustom);
        row.appendChild(enemyIsCustomCell);

        TABLEBODY.appendChild(row);
        managePopover(row);
    }

    makeGetRequest();
    ADDENEMYLOCATIONS.addEventListener(`change`, handleSelectedToArray);
    ADDENEMYSUBMIT.addEventListener(`click`, handleAddFormSubmit);
    UPDATEENEMYMODAL.addEventListener(`shown.bs.modal`, setupFindEnemy);
    UPDATEENEMYLOCATIONS.addEventListener(`change`, handleSelectedToArray);
    UPDATEENEMYSUBMIT.addEventListener(`click`, handleUpdateFormSubmit);
    DELETEENEMYMODAL.addEventListener(`shown.bs.modal`, setupDeleteEnemyModal);
    DELETEENEMYBUTTON.addEventListener(`click`, handleDeleteEnemy);

    document.addEventListener(`click`, function (event) {
        if (event.target && event.target.id === `btnView`) {
            viewEnemyPage(event);
        }
    })

})();
