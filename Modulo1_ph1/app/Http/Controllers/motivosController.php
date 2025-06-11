<?php

namespace App\Http\Controllers;

use App\Models\Motivos;
use App\Models\Solicitudayuda;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class motivosController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $ayudaas = Solicitudayuda::with(['motivo', 'ubicacion', 'datosSocioeconomicos'])->get();

        return response()->json([
            'solicitudes' => $ayudaas,
            'status' => 200
        ], 200);
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
        // Validar solo el campo 'motivo'
        $validator = Validator::make($request->all(), [
            'motivo' => 'required|string|max:255',
        ]);

        if ($validator->fails()) {
            return response()->json([
                'message' => 'Error de validación',
                'errors' => $validator->errors(),
                'status' => 400
            ], 400);
        }

        // Verificar si la solicitud existe
        $solicitud = Solicitudayuda::find($id);
        if (!$solicitud) {
            return response()->json([
                'message' => 'Solicitud de ayuda no encontrada',
                'status' => 404
            ], 404);
        }

        // Crear el motivo y asociarlo a la solicitud
        $motivo = Motivos::create([
            'motivo' => $request->motivo,
            'solicitudayuda_id' => $id,
        ]);

        return response()->json([
            'message' => 'Motivo registrado exitosamente',
            'data' => $motivo,
            'status' => 201
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
