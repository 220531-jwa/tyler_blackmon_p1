let baseUrl = "http://localhost:8080";

document.getElementById("submitButton").addEventListener("click", submitForm, false);

async function submitForm(){
    let request = {
        id: 0,
        requesterId: window.sessionStorage.getItem('id'),
        fmId: document.getElementById('fmId').value,
        amount: document.getElementById('amount').value,
        type: document.getElementById('type').value,
        approved: false,
        eventDate: document.getElementById('date').value,
        eventTime: document.getElementById('time').value,
        description: document.getElementById('description').value,
        denied: false
    }

    let requestJson = JSON.stringify(request);

    let res = await fetch(
        `${baseUrl}/account/request`,
        {
            method: 'POST',
            header: {'Content-Type': 'application/json'},
            body: requestJson
        }
    )
    .then((resp) => {
        console.log(resp);
        //window.location.assign('homePage.html')
    }).catch((err) => {
        console.log(err)
    })
}