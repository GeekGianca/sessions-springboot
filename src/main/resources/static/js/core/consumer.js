"use strict";
fetchRaffles();

function fetchRaffles() {
    let selectRaffle = $('#raffles');
    $.ajax({
        url: '/list',
        type: 'GET',
        contentType: 'application/json',
        success: function (response) {
            console.log(response);
            let defaultSelect = $('<option disabled selected>Sorteos</option>');
            selectRaffle.append(defaultSelect);
            response.forEach(function (r) {
                let newOption = $('<option></option>').val('' + r.id).text(r.raffleName);
                selectRaffle.append(newOption);
            })
        },
        error: function (xhr, status, error) {
            let defaultSelect = $('<option disabled selected>-</option>');
            selectRaffle.append(defaultSelect);
        }
    });
}

let selectElement = document.getElementById("raffles");
selectElement.addEventListener('change', function () {
    let selectedValue = $(this).val();
    $.ajax({
        url: '/--',
        type: 'GET',
        contentType: 'application/json',
        success: function (response) {
            console.log(response);
        },
        error: function (xhr, status, error) {
            console.log('Error en la petición: ', error);
        }
    });
});