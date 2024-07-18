import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { EstadosService } from './services/estados/estados.service';
import { PaisesService } from './services/paises/paises.service';
import { PersonaService } from './services/persona/persona.service';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule, HttpClientModule, ReactiveFormsModule],
  providers: [    EstadosService,    PaisesService,  PersonaService],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent implements OnInit {

  personaForm: FormGroup;
  paises: any;
  estado: any;
  persona: any;

  constructor(
    private fb: FormBuilder,
    public estadosService: EstadosService,
    public paisesService: PaisesService,
    public personaService: PersonaService
  ) {}

  ngOnInit(): void {
    this.personaForm = this.fb.group({
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      edad: ['', Validators.required],
      pais: ['', Validators.required],
      estado: ['', Validators.required]
    });


   this.paisesService.getAllPaises().subscribe(resp => {
      this.paises = resp;
    },
      error => (console.error(error))
    )

    this.personaForm.get('pais')?.valueChanges.subscribe(value=>{
      this.estadosService.getEstadoByPais(value.id).subscribe(resp => {
        this.estado = resp;
      },
        error => (console.error(error))
      )
    })
  }

  guardar() {
    this.personaService.savePersona(this.personaForm.value).subscribe(resp => {
      this.estado = resp;
    },
      error => (console.error(error))
    )
  }
}

