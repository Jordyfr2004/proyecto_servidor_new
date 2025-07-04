<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Motivos extends Model
{
    use HasFactory;

    protected $table = 'motivos';

    protected $fillable = [
        'motivo'
    ];

    public function solicitud()
    {
        return $this->belongsTo(Solicitudayuda::class, 'solicitudayuda_id');
    }
        
}
