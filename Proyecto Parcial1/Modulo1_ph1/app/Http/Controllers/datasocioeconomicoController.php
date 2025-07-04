<?php

namespace App\Http\Controllers;

use App\Models\DatoSocioeconomico;
use App\Models\Solicitudayuda;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class datasocioeconomicoController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        //
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request, $id)
    {
        // Validar los datos
        $validator = Validator::make($request->all(), [
            'numero_convivientes' => 'required|string',
            'nivel_educativo' => 'required|string',
            'ocupacion' => 'required|string',
            'tipo_vivienda' => 'required|string',
            'condicion_salud' => 'required|string',
        ]);

        if ($validator->fails()) {
            return response()->json([
                'message' => 'Error de validación',
                'errors' => $validator->errors()
            ], 400);
        }

        // Verificar que exista la solicitud
        $solicitud = Solicitudayuda::find($id);
        if (!$solicitud) {
            return response()->json([
                'message' => 'Solicitud de ayuda no encontrada'
            ], 404);
        }

        // Crear y asociar el dato socioeconómico
        $dato = DatoSocioeconomico::create([
            'numero_convivientes' => $request->numero_convivientes,
            'nivel_educativo' => $request->nivel_educativo,
            'ocupacion' => $request->ocupacion,
            'tipo_vivienda' => $request->tipo_vivienda,
            'condicion_salud' => $request->condicion_salud,
            'solicitudayuda_id' => $id,
        ]);

        return response()->json([
            'message' => 'Datos socioeconómicos registrados correctamente',
            'data' => $dato
        ], 201);
    }


    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(string $id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, string $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        //
    }
}
