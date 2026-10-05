export * from '@/lib/types/anonymous/auth.schema'

export interface ModificationResult<T> {
    result : T
}

export type Role = "Administrator" | "Management" | "Partner" | "Employee"