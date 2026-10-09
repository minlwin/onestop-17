import { Control, Controller, FieldValues, Path } from "react-hook-form";
import { Field, FieldError, FieldLabel } from "../ui/field";
import { Textarea } from "../ui/textarea";

interface FormsTextareaProps<T extends FieldValues> {
    control : Control<T>
    name : Path<T>
    label? : string
    placeholder? : string
    className? : string
}

export default function FormsTextarea<T extends FieldValues>({ control, name, label, placeholder, className } : FormsTextareaProps<T>) {
    return (
        <Controller control={control} name={name} render={({field, fieldState}) => (
            <Field data-invalid={fieldState.invalid} className={className}>
                {label && 
                    <FieldLabel className="flex items-center justify-between">
                        <span>{label}</span>
                    </FieldLabel>
                }
                <Textarea {...field} placeholder={placeholder || `Enter ${label}`} autoComplete="off" />
                {fieldState.invalid && 
                    <FieldError errors={[fieldState.error]} />
                }
            </Field>
        )} />
    )
}