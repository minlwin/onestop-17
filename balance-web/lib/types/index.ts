export * from '@/lib/types/anonymous/auth.schema'
export * from "@/lib/types/commons/sidebar-model"
export * from "@/lib/types/management/user.model"
export * from "@/lib/types/management/master.model"

export interface ModificationResult<T> {
    result : T
}

export type Role = "Administrator" | "Management" | "Partner" | "Employee"

export interface SelectOption {
    value : string
    label : string
}

export interface AuditInfo {
    createdAt: string
    createdBy: string
    modifiedAt: string
    modifiedBy: string
}
