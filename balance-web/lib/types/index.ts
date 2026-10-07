export * from '@/lib/types/anonymous/auth.schema'
export * from "@/lib/types/commons/sidebar-model"

export interface ModificationResult<T> {
    result : T
}

export type Role = "Administrator" | "Management" | "Partner" | "Employee"

export interface SelectOption {
    value : string
    label : string
}