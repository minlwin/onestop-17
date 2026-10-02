export default function AppTitle({ title, subTitle } : { title : string, subTitle? : string }) {
    return (
        <header>
            <h1 className="text-2xl font-semibold">{title}</h1>
            {subTitle && 
                <p className="text-gray-600">{subTitle}</p>
            }
        </header>
    )
}