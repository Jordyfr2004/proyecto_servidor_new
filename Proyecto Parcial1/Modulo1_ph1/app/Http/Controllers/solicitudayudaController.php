<?php

namespace App\Http\Controllers;


use Illuminate\Http\Request;
use App\Models\Solicitudayuda;
use Illuminate\Support\Facades\Validator;

class solicitudayudaController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $ayudaas = Solicitudayuda::with(['motivos', 'ubicacion', 'datosSocioeconomicos'])->get();

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
    public function store(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'nombre'        => 'required|string|max:100',
            'apellido'      => 'required|string|max:100',
            'cedula'        => 'required|string|max:10|unique:solicitudayuda,cedula',
            'telefono'      => 'required|string|max:10',
            'correo'        => 'required|email',
            'estado_civil'  => 'required|string|max:50',
        ]);

        if ($validator->fails()) {
            return response()->json([
                'message' => 'Error en la validación',
                'errors'  => $validator->errors(),
                'status'  => 400
            ], 400);
        }

        $solicitud = Solicitudayuda::create([
            'nombre'        => $request->nombre,
            'apellido'      => $request->apellido,
            'cedula'        => $request->cedula,
            'telefono'      => $request->telefono,
            'correo'        => $request->correo,
            'estado_civil'  => $request->estado_civil,
            // estado y fecha_solicitud se llenan automáticamente
        ]);

        return response()->json([
            'message' => 'Solicitud creada exitosamente',
            'data'    => $solicitud,
            'status'  => 201
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
