<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Solicitudayuda extends Model
{
    use HasFactory;

    protected $table = 'solicitudayuda';

    protected $fillable = [
        'nombre',
        'apellido',
        'cedula',
        'telefono',
        'correo',
        'estado_civil',
        'estado',
        'fecha_solicitud',
    ];
    public function motivos()
    {
        return $this->hasMany(Motivos::class, 'solicitudayuda_id');
    }

    public function ubicacion()
    {
        return $this->hasOne(Ubicacion::class, 'solicitudayuda_id');
    }

    public function datosSocioeconomicos()
    {
        return $this->hasOne(DatoSocioeconomico::class, 'solicitudayuda_id');
    }

}
