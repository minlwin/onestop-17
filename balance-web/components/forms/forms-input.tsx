import { Control, Controller, FieldValues, Path } from "react-hook-form";
import { Field, FieldError, FieldLabel } from "../ui/field";
import { Input } from "../ui/input";
import React, { HTMLInputTypeAttribute } from "react";

interface FormsInputProps<T extends FieldValues> {
    control : Control<T>
    name : Path<T>
    label : string
    type? : HTMLInputTypeAttribute
    placeholder? : string
    className? : string
    action? : Readonly<React.ReactNode>
}

export default function FormsInput<T extends FieldValues>({ control, name, label, type, placeholder, className, action } : FormsInputProps<T>) {
    return (
        <Controller control={control} name={name} render={({field, fieldState}) => (
            <Field data-invalid={fieldState.invalid} className={className}>
                <FieldLabel className="flex items-center justify-between">
                    <span>{label}</span>
                    {action && action}
                </FieldLabel>
                <Input {...field} placeholder={placeholder || `Enter ${label}`} autoComplete="off" type={type || 'text'} />
                {fieldState.invalid && 
                    <FieldError errors={[fieldState.error]} />
                }
            </Field>
        )} />
    )
}