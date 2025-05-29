import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  AbstractControl,
  ValidatorFn,
  ReactiveFormsModule,
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';

// ✅ Validador personalizado para campos que deben coincidir (documento, correo, contraseña)
export function passwordMatchValidator(
  controlName: string,
  matchingControlName: string
): ValidatorFn {
  return (formGroup: AbstractControl): { [key: string]: any } | null => {
    const control = formGroup.get(controlName);
    const matchingControl = formGroup.get(matchingControlName);

    if (!control || !matchingControl) return null;

    if (matchingControl.errors && !matchingControl.errors['passwordMismatch']) return null;

    if (control.value !== matchingControl.value) {
      matchingControl.setErrors({ passwordMismatch: true });
    } else {
      const { passwordMismatch, ...restErrors } = matchingControl.errors || {};
      matchingControl.setErrors(Object.keys(restErrors).length > 0 ? restErrors : null);
    }

    return null;
  };
}

@Component({
  selector: 'app-registro',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule,
    RouterModule,
  ],
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css'],
})
export class RegistroComponent implements OnInit {
  registerForm!: FormGroup;

  documentTypes: string[] = ['CC', 'TI', 'PASSAPORTE'];
  countryCodes: string[] = ['+57 CO'];

  constructor(private fb: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group(
      {
        documentType: ['CC', Validators.required],
        documentNumber: ['', [Validators.required, Validators.pattern(/^\d+$/)]],
        confirmDocumentNumber: ['', [Validators.required, Validators.pattern(/^\d+$/)]],
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        confirmEmail: ['', [Validators.required, Validators.email]],
        countryCode: ['+57 CO', Validators.required],
        phoneNumber: ['', [Validators.required, Validators.pattern(/^\d+$/)]],
        address: ['', Validators.required],
        password: ['', [Validators.required, Validators.minLength(6)]],
        confirmPassword: ['', Validators.required],
        acceptTerms: [false, Validators.requiredTrue],
      },
      {
        validators: [
          passwordMatchValidator('password', 'confirmPassword'),
          passwordMatchValidator('documentNumber', 'confirmDocumentNumber'),
          passwordMatchValidator('email', 'confirmEmail'),
        ],
      }
    );
  }

  get f() {
    return this.registerForm.controls;
  }

  isInvalid(controlName: string): boolean {
    const control = this.f[controlName];
    return control.invalid && (control.dirty || control.touched);
  }

  onSubmit(): void {
    if (this.registerForm.valid) {
      console.log('Formulario enviado:', this.registerForm.value);
      alert('¡Registro exitoso!');
    } else {
      console.warn('Formulario inválido');
      this.registerForm.markAllAsTouched();
      alert('Por favor, completa todos los campos requeridos correctamente.');
    }
  }

  onCancel(): void {
    this.router.navigate(['/login']);
  }
}
