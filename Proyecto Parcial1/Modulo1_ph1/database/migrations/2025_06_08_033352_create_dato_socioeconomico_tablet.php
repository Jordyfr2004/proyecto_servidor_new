<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('dato_socioeconomico', function (Blueprint $table) {
            $table->id();
            $table->string('numero_convivientes');
            $table-> string('nivel_educativo');
            $table-> string('ocupacion');
            $table->string('tipo_vivienda');
            $table->string('condicion_salud');

            $table->foreignId('solicitudayuda_id')->constrained('solicitudayuda')->onDelete('cascade');

            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('dato_socioeconomico');
    }
};
