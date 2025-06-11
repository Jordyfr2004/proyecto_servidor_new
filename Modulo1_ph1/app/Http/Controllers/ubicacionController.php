<?php

namespace App\Http\Controllers;

use App\Models\Solicitudayuda;
use App\Models\Ubicacion;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class ubicacionController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $ubi= Ubicacion::all();

        $data =[
            'ubicacion' => $ubi,
            'status' => 200
        ];

        return response()->json($data,200);
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
        try {
            // Validar los campos
            $request->validate([
                'direccion' => 'required|string',
                'ciudad' => 'required|string',
                'provincia' => 'required|string',
            ]);

            // Verificar que exista la solicitud
            $solicitud = Solicitudayuda::find($id);
            if (!$solicitud) {
                return response()->json([
                    'message' => 'Solicitud de ayuda no encontrada'
                ], 404);
            }

            // Crear la ubicación asociada
            $ubicacion = Ubicacion::create([
                'direccion' => $request->direccion,
                'ciudad' => $request->ciudad,
                'provincia' => $request->provincia,
                'solicitudayuda_id' => $id
            ]);

            return response()->json([
                'message' => 'Ubicación asociada correctamente a la solicitud',
                'data' => $ubicacion
            ], 201);
        } catch (\Exception $e) {
            return response()->json([
                'error' => 'Error interno',
                'exception' => $e->getMessage()
            ], 500);
        }
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
