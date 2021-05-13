'use strict';

(function () {
    const BASEURL = `http://localhost:8080`;
    const IMGNAMECARD = document.querySelector(`#imgNameCard`);
    const GENERALINFOCARD = document.querySelector(`#generalInfoCard`);
    const STATSCARD = document.querySelector(`#statsCard`);
    const REACTIONSCARD = document.querySelector(`#reactionsCard`);
    const ITEMSCARD = document.querySelector(`#itemsCard`);

    const getEnemy = () => {
        const queryString = window.location.search;
        const urlParams = new URLSearchParams(queryString);

        if (urlParams.has(`id`)) {
            findEnemyByID(urlParams.get(`id`));
        }
    };

    const findEnemyByID = async (id) => {
        try {
            const response = await axios.get(`${BASEURL}/profile/${id}`);
            let enemy = response.data;
            console.log(`GET: Found enemy`, enemy);
            loadEnemy(enemy);
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

    const loadEnemy = (enemy) => {

        //Set up name and image of enemy card
        let enemyImg = undefined;
        if (enemy.isCustom) {
            enemyImg = document.createElement(`i`);
            enemyImg.setAttribute(`class`, `bi ${setIconForEnemyTable(enemy)}`)
        } else {
            enemyImg = document.createElement(`img`);
            enemyImg.setAttribute(`src`, `../res/normal_enemy/${enemy.name}.png`);
            enemyImg.setAttribute(`alt`, `Image of ${enemy.name}`);
        }
        IMGNAMECARD.prepend(enemyImg);

        let enemyTitle = IMGNAMECARD.firstElementChild.nextElementSibling.firstElementChild;
        enemyTitle.innerHTML = enemy.name;

        // Set up general info card
        let generalInfoTable = GENERALINFOCARD.firstElementChild.nextElementSibling;
        let enemyType = generalInfoTable.tBodies[0].rows[0].cells[1];
        let enemyState = generalInfoTable.tBodies[0].rows[1].cells[1];
        let enemyLocations = generalInfoTable.tBodies[0].rows[2].cells[1];

        enemyType.innerHTML = enemy.enemyType;
        enemyState.innerHTML = enemy.isCustom ? `Custom` : `Normal`;
        enemyLocations.innerHTML = enemy.locations;

        // Set up stats card
        let statsTable = STATSCARD.firstElementChild.nextElementSibling;
        let enemyLevel = statsTable.tBodies[0].rows[0].cells[1];
        let enemyEXP = statsTable.tBodies[0].rows[1].cells[1];
        let enemyCoins = statsTable.tBodies[0].rows[2].cells[1];
        let enemyHP = statsTable.tBodies[0].rows[3].cells[1];
        let enemyPOW = statsTable.tBodies[0].rows[4].cells[1];
        let enemyDEF = statsTable.tBodies[0].rows[5].cells[1];
        let enemySPEED = statsTable.tBodies[0].rows[6].cells[1];

        enemyLevel.innerHTML = enemy.level;
        enemyEXP.innerHTML = enemy.exp;
        enemyCoins.innerHTML = enemy.coins;
        enemyHP.innerHTML = enemy.maxHP;
        enemyPOW.innerHTML = enemy.pow;
        enemyDEF.innerHTML = enemy.def;
        enemySPEED.innerHTML = enemy.speed;

        //Set up reactions card
        let reactionsTable = REACTIONSCARD.firstElementChild.nextElementSibling;
        let enemyJump = reactionsTable.tBodies[0].rows[0].cells[1];
        let enemyHammer = reactionsTable.tBodies[0].rows[1].cells[1];
        let enemyHand = reactionsTable.tBodies[0].rows[2].cells[1];
        let enemyFire = reactionsTable.tBodies[0].rows[3].cells[1];
        let enemyThunder = reactionsTable.tBodies[0].rows[4].cells[1];
        let enemyBurn = reactionsTable.tBodies[0].rows[5].cells[1];
        let enemyStun = reactionsTable.tBodies[0].rows[6].cells[1];
        let enemyStatDown = reactionsTable.tBodies[0].rows[7].cells[1];

        enemyJump.innerHTML = enemy.jump;
        enemyHammer.innerHTML = enemy.hammer;
        enemyHand.innerHTML = enemy.hand;
        enemyFire.innerHTML = enemy.fire;
        enemyThunder.innerHTML = enemy.thunder;
        enemyBurn.innerHTML = `${enemy.chanceOfBurn}%`;
        enemyStun.innerHTML = `${enemy.chanceOfStun}%`;
        enemyStatDown.innerHTML = `${enemy.chanceOfStatDown}%`;


        //Set up items card
        let itemsTable = ITEMSCARD.firstElementChild.nextElementSibling;
        let enemyItemOne = itemsTable.tBodies[0].rows[0].cells[1];
        let enemyItemOneChance = itemsTable.tBodies[0].rows[1].cells[1];
        let enemyItemTwo = itemsTable.tBodies[0].rows[2].cells[1];
        let enemyItemTwoChance = itemsTable.tBodies[0].rows[3].cells[1];

        enemyItemOne.innerHTML = enemy.itemOne;
        enemyItemOneChance.innerHTML = `${enemy.itemOneChance}%`;
        enemyItemTwo.innerHTML = enemy.itemTwo;
        enemyItemTwoChance.innerHTML = `${enemy.itemTwoChance}%`;
    };

    getEnemy();
})();