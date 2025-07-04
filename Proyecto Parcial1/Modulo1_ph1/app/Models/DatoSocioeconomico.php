<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class DatoSocioeconomico extends Model
{
    use HasFactory;

    protected $table ='dato_socioeconomico';

    protected $fillable = [
        'numero_convivientes',
        'nivel_educativo',
        'ocupacion',
        'tipo_vivienda',
        'condicion_salud',
        'solicitudayuda_id',
    ];

    public function solicitud()
    {
        return $this->belongsTo(Solicitudayuda::class, 'solicitudayuda_id');
    }
}
