<?php

use App\Http\Controllers\motivosController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\solicitudayudaController;
use App\Http\Controllers\ubicacionController;
use App\Http\Controllers\datasocioeconomicoController;

Route::get('/user', function (Request $request) {
    return $request->user();
})->middleware('auth:sanctum');


Route::get('/info',[solicitudayudaController::class,'index']);
Route::post('/soli',[solicitudayudaController::class,'store']);
Route::get('/motivos',[motivosController::class,'index']);
Route::post('/ingresar_motivo/{id}',[motivosController::class,'store']);
Route::get('/ver_ubicacion',[ubicacionController::class,'index']);
Route::post('/ingresar_ubi/{id}',[ubicacionController::class,'store']);
Route::post('/ingresar_dato_socio/{id}', [datasocioeconomicoController::class, 'store']);