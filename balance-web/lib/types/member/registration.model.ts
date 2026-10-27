import z from "zod";

export const registrationSchema = z.object({
    companyName: z.string().nonempty("Enter company name"),
    category: z.string().nonempty("Enter company category"),
    address: z.string().nonempty("Enter company address"),
    phone: z.string().nonempty("Enter partner phone"),
    position: z.string().nonempty("Enter partner position in company"),
    remark : z.string()
})

export type RegistrationForm = z.infer<typeof registrationSchema>