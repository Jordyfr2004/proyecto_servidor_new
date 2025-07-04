<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Ubicacion extends Model
{
    use HasFactory;

    protected $table ='ubicacion';

    protected $fillable = [
        'direccion',
        'ciudad',
        'provincia',
        'solicitudayuda_id'
    ];

    public function solicitud()
    {
        return $this->belongsTo(Solicitudayuda::class, 'solicitudayuda_id');
    }

}
