import { Control, Controller, FieldValues, Path } from "react-hook-form";
import { Field, FieldError, FieldLabel } from "../ui/field";
import { NativeSelect, NativeSelectOption } from "../ui/native-select";
import { SelectOption } from "@/lib/types";

interface FormsSelectProps<T extends FieldValues> {
    control : Control<T>
    name : Path<T>
    label : string
    className? : string
    options: SelectOption[]
}

export default function FormsSelect<T extends FieldValues>({ control, name, label, className, options } : FormsSelectProps<T>) {
    return (
        <Controller control={control} name={name} render={({field, fieldState}) => (
            <Field data-invalid={fieldState.invalid} className={className}>
                <FieldLabel className="flex items-center justify-between">
                    <span>{label}</span>
                </FieldLabel>
                <NativeSelect {...field}>
                    {options.map((item, index) => 
                        <NativeSelectOption key={index} value={item.value}>
                            {item.label}
                        </NativeSelectOption>
                    )}
                </NativeSelect>
                {fieldState.invalid && 
                    <FieldError errors={[fieldState.error]} />
                }
            </Field>
        )} />
    )
}